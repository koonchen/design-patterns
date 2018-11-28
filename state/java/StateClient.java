interface State {
  void handle();
}

class ConcreteState implements State {

  @Override
  public void handle() {
    System.out.println("ConcreteState instance has been created");
  }
}

class Context {
  State state;

  public void setState(State state) {
    this.state = state;
  }

  void request() {
    this.state.handle();
  }
}

public class StateClient {
  public static void main(String[] args) {
    State state = new ConcreteState();
    Context context = new Context();
    context.setState(state);
    context.request();
  }
}
