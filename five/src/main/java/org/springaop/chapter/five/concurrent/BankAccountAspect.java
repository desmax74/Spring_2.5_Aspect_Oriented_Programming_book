package org.springaop.chapter.five.concurrent;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springaop.utils.Constants;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Aspect
public class BankAccountAspect {

    @Pointcut("execution(* org.springaop.chapter.five.concurrent.BankAccount.getBalance())")
        public void safeRead() {}

        @Pointcut("execution(* org.springaop.chapter.five.concurrent.BankAccount.creditOperation(*,*)) ")
        public void stateModificationCredit() {}

        @Pointcut("execution(* org.springaop.chapter.five.concurrent.BankAccount.debitOperation(*,*)) ")
        public void stateModificationDebit() {}

        @Pointcut("execution(* org.springaop.chapter.five.concurrent.BankAccount.getId())")
        public void getId() {}

        @Pointcut("execution(* org.springaop.chapter.five.concurrent.BankAccount.getStartDate())")
        public void getStartDate() {}

        @Before("safeRead()")
        public void beforeSafeRead() {
            if (log.isInfoEnabled()) {
                log.info("setReadLock");
            }
            rLock.lock();
        }

        @After("safeRead()")
        public void afterSafeRead() {
            rLock.unlock();
            if (log.isInfoEnabled()) {
                log.info("releaseReadLock");
            }
        }

        @Before("stateModificationCredit() || stateModificationDebit()")
        public void beforeSafeWrite() {
            if (log.isInfoEnabled()) {
                log.info("setWriteLock");
            }
            wLock.lock();
        }

        @After("stateModificationCredit() || stateModificationDebit()")
        public void afterSafeWrite() {
            wLock.unlock();
            if (log.isInfoEnabled()) {
                log.info("releaseWriteLock");
            }
        }

        private final ReadWriteLock lock = new ReentrantReadWriteLock();
        private final Lock rLock = lock.readLock();
        private final Lock wLock = lock.writeLock();
        private Logger log = Logger.getLogger(Constants.LOG_NAME);
}
