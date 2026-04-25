package com.udemy.hexagonal.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.boot.test.context.SpringBootTest;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@SpringBootTest
@AnalyzeClasses(packages = "com.udemy.hexagonal")
public class LayeredArchitectureTest {

    @ArchTest
    public static final ArchRule layeredArchitectureRule = layeredArchitecture()
            .consideringAllDependencies()
            .layer("AdaptersIn").definedBy("..adapters.in..")
            .layer("AdaptersOut").definedBy("..adapters.out..")
            .layer("UseCase").definedBy("..application.core.usecase..")
            .layer("PortsIn").definedBy("..application.ports.in..")
            .layer("PortsOut").definedBy("..application.ports.out..")
            .layer("Config").definedBy("..config..")
            .whereLayer("AdaptersIn").mayOnlyBeAccessedByLayers("Config")
            .whereLayer("AdaptersOut").mayOnlyBeAccessedByLayers("Config")
            .whereLayer("UseCase").mayOnlyBeAccessedByLayers("Config")
            .whereLayer("PortsIn").mayOnlyBeAccessedByLayers("Config", "UseCase", "AdaptersIn")
            .whereLayer("PortsOut").mayOnlyBeAccessedByLayers("Config", "UseCase", "AdaptersOut")
            .whereLayer("Config").mayNotBeAccessedByAnyLayer();
}
