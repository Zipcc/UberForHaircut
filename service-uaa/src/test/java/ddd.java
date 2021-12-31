
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ddd {

    String name;
    // 私钥:
    PrivateKey sk;
    // 公钥:
    PublicKey pk;

    //创建令牌
    @Test
    public void createJWT() throws GeneralSecurityException {

        byte[] message = "test".getBytes(StandardCharsets.UTF_8);
        ClassPathResource classPathResource = new ClassPathResource("jianchenkey.jks");
        KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(classPathResource, "jianchens".toCharArray());
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair("jianchena", "jianchenp".toCharArray());
        PrivateKey privateKey = keyPair.getPrivate();
        sk = (RSAPrivateKey) privateKey;
        pk = (RSAPublicKey) keyPair.getPublic();

        System.out.println(Arrays.toString((encrypt(message))));
        byte[] se = encrypt(message);
        byte[] out = decrypt(se);
        System.out.println(new String(out));

    }
        Map<String, Object> tokenMap = new HashMap<>();
        public byte[] getPrivateKey() {
            return this.sk.getEncoded();
        }

        // 把公钥导出为字节
        public byte[] getPublicKey() {
            return this.pk.getEncoded();
        }

        // 用公钥加密:
        public byte[] encrypt(byte[] message) throws GeneralSecurityException {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, this.pk);
            return cipher.doFinal(message);
        }

        // 用私钥解密:
        public byte[] decrypt(byte[] input) throws GeneralSecurityException {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, this.sk);
            return cipher.doFinal(input);
        }


    //解析令牌
  /*  public void parseJWT() {
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlcyI6IlJPTEVfVklQLFJPTEVfVVNFUiIsIm5hbWUiOiJyb2JvZCIsImlkIjoiMSJ9.KkPXXlYTkDCWq2VN5qy6w6FI5TgCbIy-GkQShaYGbfvcZsj0165XsgXSx7Sf5aUpGB-494ds-ZnLxs3oVMZ7_tbu-is1-gZOeQ0G1GLla0ytNkImXabnujgWH2B4bmX4lBLK7d8xTEQ4WoAnydWUusCmPjQDgdFZGHmccJLuYKqQPzru-4go1mFgjEeB7gNu6cLYyQc79bZdF2Mk2OX1Nxpb88sux0QkNAlb1-JuUhmjbUYwMK5l5W5zeNckRJtGy_Zy7OTwXviuRp6uISmWD7p1HYbkKH-ROKCgSu1cqnok0645Uou7Y54Nd8NosIqShuNYbBBo_BHWuyx_lKdk1g";
        String publicKey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiq6KfbXc/viuB6oQ/80cfLSFIr7pX3PmteAQ2/dA+ReMLgULJb+U8Dax3xNpBgLAp+Ei2IMkBFJlJRn/iaYi5eMnCY2vyfHkC69x6OhhCtzWBRxGJkPRjLDU+Obhak2MrDI4zIpzQs2/phjqWXuEPMz7KMd5UhoAFZWLTW1Ih3CP962fuJdV83hj/2uWN/yaAgaLRxRlTw7HHoIEy1dX9prAnqQ/rOl2Igvwi23GNnzMrqlvR9qt1gBI+noHtMv07hkavUT1nmoYnt/pw2+FLMLFEun2gR3DUmqu79QC6trDf3cVfKyRP9A7TBjUEv+Ecrh8JQosQa8GongTzHhmOwIDAQAB-----END PUBLIC KEY-----";
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publicKey));
        String claims = jwt.getClaims();
        System.out.println(claims);
    }*/
}
