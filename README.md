# gRPC Server in Java (grpc-server-java)

gRPC is a modern open source high performance RPC framework that can run in any environment. It can efficiently connect services in and across data centers with pluggable support for load balancing, tracing, health checking and authentication. It is also applicable in last mile of distributed computing to connect devices, mobile applications and browsers to backend services.[https://grpc.io].

gRPC Server project was created by Java. The contents consist of 4 RPC types call. I wrote for testing purpose only on Swift iOS as client side. How Swift iOS handle gRPC communication from server we can see later in another my source code 
#### 1. Unary Call
One request from client, one response from server (Like REST)
#### 2. Client Streaming Call
Streaming request from client, one response from server
#### 3. Server Streaming Call
One request from client, streaming response from server
#### 4. Client Server Streaming Call (Bidirectional Streaming)
Streaming request from client, streaming response from server


