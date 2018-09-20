package xyz.leefly.test;

import org.mindrot.jbcrypt.BCrypt;

public class Test {

    @org.junit.Test
    public void testEcr() {
        String hashpw = BCrypt.hashpw("123456", BCrypt.gensalt());
        System.out.println(hashpw);
        boolean checkpw = BCrypt.checkpw("123456", "$2a$10$CjU6wS1iJeFtn.w1/pMy6upMLgd06ClLxHWzRDaJyE28V1dMJYC9S");
        System.out.println(checkpw);
    }

}
