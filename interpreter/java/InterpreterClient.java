import java.util.ArrayList;
import java.util.List;

class Context {
  // 存一些全局信息
  private String name = "Context";

  public String getName() {
    return name;
  }
}

interface AbstractExpression {
  void interpret(Context context);
}

class TerminalExpression implements AbstractExpression {

  @Override
  public void interpret(Context context) {
    System.out.println("TerminalExpression instance has been created");
  }
}

class NonterminalExpression implements AbstractExpression {

  private List<AbstractExpression> list = null;
  private String name;

  public NonterminalExpression(String name) {
    this.name = name;
  }

  public void setList(List<AbstractExpression> list) {
    this.list = new ArrayList<>();
    for (AbstractExpression expression: list) {
      this.list.add(expression);
    }
  }

  @Override
  public void interpret(Context context) {
    if (this.list != null) {
      for (AbstractExpression expression: this.list) {
        expression.interpret(context);
      }
    }
    System.out.println(this.name + " instance has been created");
  }
}

public class InterpreterClient {
  public static void main(String[] args) {
    Context context = new Context();
    AbstractExpression aRepetition = new NonterminalExpression("repetition");
    AbstractExpression dogsLiteral = new NonterminalExpression("dogs");
    AbstractExpression catsLiteral = new NonterminalExpression("cats");
    List<AbstractExpression> list = new ArrayList<>();
    list.add(dogsLiteral);
    list.add(catsLiteral);
    ((NonterminalExpression) aRepetition).setList(list);
    aRepetition.interpret(context);
  }
}
