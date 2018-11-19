class Invoker {
  Command command = null;

  public void setCommand(Command command) {
    this.command = command;
  }

  public void doSth() {
    this.command.execute();
  }
}

class Receiver {
  public void action() {
    System.out.println("action method has been created");
  }
}

interface Command {
  void execute();
}

class ConcreteCommand implements Command {

  Receiver receiver = null;

  public void setReceiver(Receiver receiver) {
    this.receiver = receiver;
  }

  @Override
  public void execute() {
    this.receiver.action();
  }
}

public class CommandClient {

  public static void main(String[] args) {
    Command command = new ConcreteCommand();
    ((ConcreteCommand) command).setReceiver(new Receiver());

    Invoker invoker = new Invoker();
    invoker.setCommand(command);

    invoker.doSth();
  }
}
