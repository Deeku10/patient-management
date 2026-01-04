package in.bydeepak.patientservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class BillingServiceGrpcClient {

    private static final Logger log = LoggerFactory.getLogger(
            BillingServiceGrpcClient.class);

    private final BillingServiceGrpc.BillingServiceBlockingStub billingServiceBlockingStub;

    public BillingServiceGrpcClient(@Value("${billing.service.address}") String address,@Value("${billing.service.port}") int port) {
        log.info("Connecting to Billing Service GRPC service at {}:{}",
                address, port);

        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress(address,port).usePlaintext().build();

        billingServiceBlockingStub = BillingServiceGrpc.newBlockingStub(managedChannel);
    }

    public BillingResponse createBillingAccount(String patientId,String name,String email){

        BillingRequest request = BillingRequest.newBuilder().setName(name).setEmail(email).build();

        return  billingServiceBlockingStub.createBillingAccount(request);
    }
}
