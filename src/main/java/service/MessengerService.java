package service;

import com.system.messenger.MessengerGrpc;
import io.grpc.stub.StreamObserver;
import listener.IResponseObserver;

import static com.system.messenger.MessengerProto.*;

public class MessengerService extends MessengerGrpc.MessengerImplBase {

    IResponseObserver iResponseObserver;

    public MessengerService(IResponseObserver responseObserver) {
        this.iResponseObserver = responseObserver;
    }

    @Override
    public void getMessageUnary(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
        String output = request.getText();
        MessageResponse response = MessageResponse.newBuilder().setText(output).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        System.out.println("Message (Unary Call) : " + output);
    }

    @Override
    public void getMessageServerStream(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
        iResponseObserver.setupResponseObserver(responseObserver);
        MessageResponse responseStatus = MessageResponse.newBuilder().setText(request.getText()).build();
        responseObserver.onNext(responseStatus);
        System.out.println("Message (Server Stream) : " + request.getText());
    }

    @Override
    public StreamObserver<MessageRequest> getMessageClientStream(StreamObserver<MessageResponse> responseObserver) {
        return new StreamObserver<MessageRequest>() {
            @Override
            public void onNext(MessageRequest value) {
                System.out.println("Message (Client Stream) : " + value.getText());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getLocalizedMessage());
            }

            @Override
            public void onCompleted() {
                String message = "Stream from client is completed";
                MessageResponse response = MessageResponse.newBuilder().setText(message).build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        };

    }

    @Override
    public StreamObserver<MessageRequest> getMessageClientServerStream(StreamObserver<MessageResponse> responseObserver) {
        return new StreamObserver<MessageRequest>() {
            @Override
            public void onNext(MessageRequest value) {
                MessageResponse responseStatus = MessageResponse.newBuilder().setText(value.getText()).build();
                responseObserver.onNext(responseStatus);
                iResponseObserver.setupResponseObserver(responseObserver);
                System.out.println("Message (Client Server Stream) : " + value.getText());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getLocalizedMessage());
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
