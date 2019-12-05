# gRPC Server in Java (grpc-server-java)

gRPC is a modern open source high performance RPC framework that can run in any environment. It can efficiently connect services in and across data centers with pluggable support for load balancing, tracing, health checking and authentication. It is also applicable in last mile of distributed computing to connect devices, mobile applications and browsers to backend services.[https://grpc.io].

gRPC Server project was created by Java. The contents consist of 4 RPC service types call with **Messenger.proto** as the protobuf file.


### Messenger.proto
``` syntax = "proto3";
option java_package = "com.system.messenger";
option java_outer_classname = "MessengerProto";

service Messenger {
  // Immediately returns an message of a request.
  rpc GetMessageUnary(MessageRequest) returns (MessageResponse) {}

  // Send message as a request and receive message from server as response stream.
  rpc GetMessageServerStream(MessageRequest) returns (stream MessageResponse) {}

  // Send message as stream and receive message from server as a response
  rpc GetMessageClientStream(stream MessageRequest) returns (MessageResponse) {}

  // Send message as stream and receive message from server as response stream
  rpc GetMessageClientServerStream(stream MessageRequest) returns (stream MessageResponse) {}
}

message MessageRequest {
  // The text of a message request
  string text = 1;
}

message MessageResponse {
  // The text of a message response.
  string text = 1;
}
```

I wrote these codes for testing purpose only on Swift iOS as client side. How Swift iOS handle gRPC communication from server we can see the source that i wrote https://github.com/freakandstein/grpc-client-swift-iOS. Here is short description about 4 RPC service type call.
#### 1. Unary Call (GetMessageUnary)
One request from client, one response from server (Like REST)
#### 2. Server Streaming Call (GetMessageServerStream)
One request from client, streaming response from server
#### 3. Client Streaming Call (GetMessageClientStream)
Streaming request from client, one response from server
#### 4. Client Server Streaming Call A.K.A Bidirectional Streaming (GetMessageClientServerStream)
Streaming request from client, streaming response from server
