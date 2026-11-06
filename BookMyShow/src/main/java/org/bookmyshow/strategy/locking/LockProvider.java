package org.bookmyshow.strategy.locking;

public interface LockProvider {
    boolean tryLock(String key, String userId, Long ttlMs);
    void unlock(String key);
    boolean isLockExpired(String key);
    boolean isLockedBy(String key, String userId);
}
