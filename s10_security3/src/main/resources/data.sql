insert into user values (1, "root", "$2a$10$ldd3zAkDpdpvteit/mOK4.GWqr3VkcCsU7tpFvt2Vx2702KNdA3eK",1, 0),
                        (2, "admin", "$2a$10$ldd3zAkDpdpvteit/mOK4.GWqr3VkcCsU7tpFvt2Vx2702KNdA3eK",1, 0),
                        (3, "wfb", "$2a$10$ldd3zAkDpdpvteit/mOK4.GWqr3VkcCsU7tpFvt2Vx2702KNdA3eK",1, 0);
insert into role values (1, "ROLE_dba", "数据库管理员"),
                        (2, "ROLE_admin", "系统管理员"),
                        (3, "ROLE_user", "用户");
insert into user_role values(1, 1, 1),
                             (2, 1, 2),
                             (3, 2, 2),
                             (4, 3, 3);