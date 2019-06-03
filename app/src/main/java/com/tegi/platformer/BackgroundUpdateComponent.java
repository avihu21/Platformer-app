package com.tegi.platformer;

class BackgroundUpdateComponent implements UpdateComponent {

    @Override
    public void update(long fps,Transform t,Transform playerTransform) {

        //cast to background transform
        BackgroundTransform bt = (BackgroundTransform) t;

        PlayerTransform pt = (PlayerTransform) playerTransform;

        float currentXClip = bt.getXClip();

        //when the player is moving right - update currentXClip to the left
        if (playerTransform.headingRight()) {
            currentXClip -= t.getSpeed() / fps;
            bt.setXClip(currentXClip);
        }

        //when the player is moving left - update currentXClip to the right
        else if (playerTransform.headingLeft()) {
            currentXClip += t.getSpeed() / fps;
            bt.setXClip(currentXClip);
        }

        //when currentXClip reduces either extreme left or right flip the order and reset currentXClip
        if (currentXClip >= t.getSize().x) {
            bt.setXClip(0);
            bt.flipReversedFirst();
        } else if (currentXClip <= 0) {
            bt.setXClip((int) t.getSize().x);
            bt.flipReversedFirst();
        }

    }
}
