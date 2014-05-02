package org.springaop.chapter.five.concurrent;

import org.apache.log4j.Logger;
import org.springaop.utils.Constants;

import java.util.Date;

public class BankAccount {

    public BankAccount(){
            this.id = 0;
            this.balance = new Float(0);
            this.startDate = new Date();
        }

        public BankAccount(Integer id) {
            this.id = id;
            this.balance = new Float(0);
            this.startDate = new Date();
        }

        public BankAccount(Integer id, Float balance) {
            this.id = id;
            this.balance = balance;
            this.startDate = new Date();
        }

        public BankAccount(Integer id, Float balance, Date start) {
            this.id = id;
            this.balance = balance;
            this.startDate = start;
        }

        public boolean debitOperation(Float debit, Float balance) {
            if (balance < debit) {
                return false;
            } else {
                setBalance(balance - debit);
                return true;
            }
        }

        public void creditOperation(Float credit, Float balance) {
            setBalance(balance+ credit);
        }


        private void setBalance(Float balance) {
            if (log.isInfoEnabled()) {
                log.info("setBalance");
            }
            this.balance = balance;
        }

        public Float getBalance() {
            if (log.isInfoEnabled()) {
                log.info(balance);
            }
            return balance;
        }

        public Integer getId() {
            return id;
        }

        public Date getStartDate() {
            return (Date) startDate.clone();
        }

        private Float balance;
        private final Integer id;
        private final Date startDate;
        private Logger log = Logger.getLogger(Constants.LOG_NAME);
}
