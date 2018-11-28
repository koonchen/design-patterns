import java.util.ArrayList;
import java.util.List;

// 观察者
interface Observer {
  void update();
}

// 目标
class Subject {
  List<Observer> list;

  public Subject() {
    list = new ArrayList<>();
  }

  void attach(Observer observer) {
    list.add(observer);
  }
  void detach(Observer observer) {
    list.remove(observer);
  }
  void subjectNotify() {
    for (Observer observer: list) {
      observer.update();
    }
  }

  String getState(){return null;}
  void setState(String state){}
}

class ConcreteSubject extends Subject {

  String subjectState;

  String getState() {
    return subjectState;
  }

  void setState(String subjectState) {
    this.subjectState = subjectState;
  }
}

class ConcreteObserver implements Observer {
  String observerState;
  Subject subject;

  public void setSubject(Subject subject) {
    this.subject = subject;
  }

  @Override
  public void update() {
    observerState = subject.getState();
    System.out.println(observerState);
  }
}

public class ObserverClient {
  public static void main(String[] args) {
    // 首先要有一个目标
    Subject subject = new ConcreteSubject();
    subject.setState("ConcreteSubject instance has been created");
    Observer observer = new ConcreteObserver();
    ((ConcreteObserver) observer).setSubject(subject);
    subject.attach(observer);
    subject.subjectNotify();
  }
}
