package org.yearup;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class EasyshopApplication {

    public static void main(MysqlxDatatypes.Scalar.String[] args) {
        SpringApplication.run(EasyshopApplication.class, Arrays.toString(args));
    }

}
