package manager;

import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import io.grpc.stub.StreamObserver;
import listener.IResponseObserver;
import service.MessengerService;

import java.io.IOException;
import java.net.InetSocketAddress;

import static com.system.messenger.MessengerProto.*;

public class ServerManager implements IResponseObserver {

    private StreamObserver<MessageResponse> responseObserverServerStream;
    private Server server;

    public ServerManager() throws InterruptedException, IOException {
        System.out.println("Server Starting...");
        server = NettyServerBuilder.forAddress(new InetSocketAddress("127.0.0.1", 8080))
                .addService(new MessengerService(this)).build();
        server.start();
        System.out.println("Port: " + server.getPort());
        System.out.println("Started");
    }


    public void stopServer() throws InterruptedException {
        server.shutdownNow();
    }

    public void sendMessageToClient(String text) {
        if (responseObserverServerStream != null) {
            MessageResponse response = MessageResponse.newBuilder().setText(text).build();
            responseObserverServerStream.onNext(response);
        }
    }

    @Override
    public void setupResponseObserver(StreamObserver<MessageResponse> responseObserver) {
        responseObserverServerStream = responseObserver;
    }
}
