package com.example.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;

/**
 * 编程事务，手动begin，手动commit,手动rollback
 *
 * @author wangbinlin
 * @version V1.0
 * @date 2019/7/25
 */
@Component
public class TransactionUtils {

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    //开启事务
    public TransactionStatus begin(){
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return  transactionStatus;
    }

    //提交事务
    public void commit(TransactionStatus status){
        dataSourceTransactionManager.commit(status);
    }

    //回滚事务
    public  void rollback(TransactionStatus status){
        dataSourceTransactionManager.rollback(status);
    }

}
