import Executor.WorkflowExecutor;
import org.apache.log4j.BasicConfigurator;

public class Main {
    public static void main(String[] args) {
        BasicConfigurator.configure();

        WorkflowExecutor executor = new WorkflowExecutor("workflow.txt");
        executor.start();
    }
}
