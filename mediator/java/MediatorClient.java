// 改变的组件
class Colleague {
  Mediator mediator;

  public void setMediator(Mediator mediator) {
    this.mediator = mediator;
  }

  public void changed() {
    mediator.widgetChanged(this);
  }

  public void doSth(){}
}

class ConcreateColleague extends Colleague {
  ConcreateColleague colleague;
  public void doSth() {
    System.out.println("ConcreateColleague instance has been created");
  }
}

// 中介者
class Mediator {
  public void showDialog() {}
  public void createWidgets() {}
  public void widgetChanged(Colleague colleague) {}
}

class ConcreteMediator extends Mediator {
  @Override
  public void widgetChanged(Colleague colleague) {
    colleague.doSth();
  }
}

public class MediatorClient {
  public static void main(String[] args) {
    Colleague colleague = new ConcreateColleague();
    Mediator mediator = new ConcreteMediator();
    mediator.widgetChanged(colleague);
  }
}
