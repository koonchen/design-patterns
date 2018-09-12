enum RequestType {
  ConcreteHandler1, ConcreteHandler2
}

class Request {
  RequestType requestType;
  String requestDescription;

  public Request(RequestType requestType, String requestDescription) {
    this.requestType = requestType;
    this.requestDescription = requestDescription;
  }

  @Override
  public String toString() {
    return "Request{" +
        "requestType=" + requestType +
        ", requestDescription='" + requestDescription + '\'' +
        '}';
  }

  public RequestType getRequestType() {
    return requestType;
  }
}

class Handler {
  private Handler next;

  public Handler(Handler next) {
    this.next = next;
  }

  void handleRequest(Request request) {
    if (next != null) {
      next.handleRequest(request);
    }
  }
}

class ConcreteHandler1 extends Handler {

  public ConcreteHandler1(Handler next) {
    super(next);
  }

  @Override
  void handleRequest(Request request) {
    if (request.getRequestType().equals(RequestType.ConcreteHandler1))
      System.out.println("ConcreteHandler1 instance has been created");
    else
      super.handleRequest(request);
  }
}

class ConcreteHandler2 extends Handler {

  public ConcreteHandler2(Handler next) {
    super(next);
  }

  @Override
  void handleRequest(Request request) {
    if (request.getRequestType().equals(RequestType.ConcreteHandler2))
      System.out.println("ConcreteHandler2 instance has been created");
    else
      super.handleRequest(request);
  }
}

public class CorClient {
  public static void main(String[] args) {
    Handler handler = new Handler(new ConcreteHandler1(new ConcreteHandler2(null)));
    handler.handleRequest(new Request(RequestType.ConcreteHandler1, "ConcreteHandler1"));
    handler.handleRequest(new Request(RequestType.ConcreteHandler2, "ConcreteHandler2"));
  }
}
