package com.goodworkalan.deviate.mix;

import com.goodworkalan.go.go.Artifact;
import com.goodworkalan.mix.BasicJavaModule;

public class DeviateModule extends BasicJavaModule {
    public DeviateModule() {
        super(new Artifact("com.goodworkalan", "deviate", "0.7"));
        addTestDependency(new Artifact("org.testng", "testng", "5.10"));
    }
}
