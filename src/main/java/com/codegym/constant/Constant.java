package com.codegym.constant;

public interface Constant {
    public enum Gender {
        Nam,
        Nữ,
        Khác,
        Mọi_Giới_Tính
    }

    public enum RoleName {
        ADMIN,
        USER,
        COMPANY
    }

    public enum Status {
        Khóa,
        Mở
    }

    public enum WorkingType {
        PART_TIME,
        FULL_TIME
    }

    public enum Proposal {
        Có,
        Không
    }

    public enum Approval {
        YES,
        NO
    }
}
