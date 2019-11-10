package listener;

import com.system.messenger.MessengerProto;
import io.grpc.stub.StreamObserver;

import static com.system.messenger.MessengerProto.*;

public interface IResponseObserver {
    void setupResponseObserver(StreamObserver<MessageResponse> responseObserver);
}
