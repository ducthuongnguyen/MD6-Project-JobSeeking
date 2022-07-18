package com.codegym.constant;

public interface Constant {
    public enum Gender {
        Male,
        Female,
        Other
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
}
