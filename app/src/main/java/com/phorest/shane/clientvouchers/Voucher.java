package com.phorest.shane.clientvouchers;

/**
 * Created by hp on 25/08/2017.
 */

public class Voucher {

    private Integer id;
    private String clientId;
    private String creatingBranchId;
    private String expiryDate;
    private String issueDate;
    private double originalBalance;

    public Voucher(String clientId, String creatingBranchId,
                   String issueDate, String expiryDate, double originalBalance) {
        this.clientId = clientId;
        this.creatingBranchId = creatingBranchId;
        this.expiryDate = expiryDate;
        this.issueDate = issueDate;
        this.originalBalance = originalBalance;
    }

    public Integer getId() {
        return id;
    }

    public double getOriginalBalance() {
        return originalBalance;
    }

}
