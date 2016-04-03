package uk.co.adamgrant.cs4099;

/**
 */
public class LockDataEntry {
    // 0 = locked, 1 = unlocked
    private int locked;
    private long epoch;

    public LockDataEntry(int locked, long epoch) {
        this.locked = locked;
        this.epoch = epoch;
    }

    public int getLocked() {
        return locked;
    }

    public boolean isLocked() {
        return locked == 0;
    }

    public long getEpoch() {
        return epoch;
    }
}
