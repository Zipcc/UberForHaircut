import com.bristol.project.utils.TokenDecoder;
import org.junit.Test;
import org.springframework.cloud.context.encrypt.EncryptorFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import java.util.HashMap;
import java.util.Map;


public class M {
    @Test
    public static void main(String[] args) {
       // EncryptorFactory encryptorFactory = new EncryptorFactory();
        //String s = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA8qh+klQJBWJyEC23srt3uSWrxd3K01vJ+LtGnx7iwyjkzg/QDr7X3/gqpYFa2rp9hxFA3otUPLTcCKQXPGYWHcpnuE/Nn5MA/zwfHemiL+UnFwPQh3XXKIdUurV1ZSgWpu9nR3rXTRZ3TaevZ+afDKvy6iRbvkJDH2mkSFR0l+u5LsIRXDZgxOgoDNBTN3QUY1RT+bpkoJtnoGl7h7s3LBO0MGERRYiiNsqQR0jXvlmX2I+W8clib6gYrQwAR6hw1oD9OlIae53p9Tc9eSGpBk1O6H2ZE5BD7NnVPGzNDXzYM5sx0rtPATC8U15Ae7gLLnUP1+PKB7hmemsrDcFT6wIDAQAB-----END PUBLIC KEY-----";
        //TextEncryptor textEncryptor = encryptorFactory.create(s);

        Map<String, String> currentUserInfo = new HashMap<>();
        currentUserInfo.put("a", "a");
        currentUserInfo.put("b", "1");
        currentUserInfo.put("c", 1111);
        String currentUsername = currentUserInfo.get("username");
        System.out.println(currentUserInfo);
    }
}
