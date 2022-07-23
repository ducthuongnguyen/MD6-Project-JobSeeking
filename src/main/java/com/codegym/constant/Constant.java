package com.codegym.constant;

public interface Constant {
    public enum Gender {
        MALE,
        FEMALE,
        OTHER,
        ALL
    }

    public enum RoleName {
        ADMIN,
        USER,
        COMPANY
    }

    public enum Status {
        LOCK,
        UNLOCK
    }

    public enum WorkingType {
        PART_TIME,
        FULL_TIME
    }

    public enum Proposal {
        YES,
        NO
    }

    public enum Approval {
        YES,
        NO
    }
}
