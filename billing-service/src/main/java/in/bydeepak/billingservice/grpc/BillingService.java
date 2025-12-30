package in.bydeepak.billingservice.grpc;

import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingService  extends BillingServiceGrpc.BillingServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BillingService.class);

    @Override
    public void createBillingAccount(billing.BillingRequest request, StreamObserver<billing.BillingResponse> streamObserver){

        log.info("createBillingAccount request recieved {}",request.toString());

        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId("12345")
                .setStatus("Active")
                .build();

        streamObserver.onNext(response);
        streamObserver.onCompleted();
    }

}
