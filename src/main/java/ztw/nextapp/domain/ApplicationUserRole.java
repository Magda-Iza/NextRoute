package ztw.nextapp.domain;


public enum ApplicationUserRole {
    ADMIN("ROLE_ADMIN"),
    EMPLOYEE("ROLE_EMPLOYEE"),
    DRIVER("ROLE_DRIVER"),
    GRACJAN("ROLE_GRACJAN");

    String userRole;

    ApplicationUserRole(String userRole) {
        this.userRole = userRole;
    }


}
