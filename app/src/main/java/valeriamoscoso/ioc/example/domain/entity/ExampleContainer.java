package valeriamoscoso.ioc.example.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class ExampleContainer {

    public ExampleContainer() {
        this.examples = new ArrayList<>();
    }

    private List<Example> examples;

    public List<Example> getExamples() {
        return examples;
    }
}
