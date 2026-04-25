package com.udemy.hexagonal.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.udemy.hexagonal")
public class NamingConventionTest {

    @ArchTest
    public static final ArchRule consumer_reside_only_consumer_package = classes()
            .that()
            .haveNameMatching(".*Consumer")
            .should()
            .resideInAPackage("..adapters.in.consumer")
            .as("Class with suffix Consumer should reside in package ..adapters.in.consumer");


    @ArchTest
    public static final ArchRule mapper_reside_only_consumer_package = classes()
            .that()
            .haveNameMatching(".*Mapper")
            .should()
            .resideInAnyPackage("..adapters.in.consumer.mapper",
                    "..adapters.in.controller.mapper",
                    "..adapters.out.client.mapper",
                    "..adapters.out.repository.mapper"
            )
            .as("Class with suffix Mapper should reside in package ..adapters.in.consumer.mapper, ..adapters.in.controller.mapper, ..adapters.out.client.mapper or ..adapters.out.repository.mapper");


    @ArchTest
    public static final ArchRule controller_reside_only_controller_package = classes()
            .that()
            .resideInAnyPackage("..adapters.in.controller")
            .should()
            .haveNameMatching(".*Controller");

    @ArchTest
    public static final ArchRule mapper_reside_only_mapper_package = classes()
            .that()
            .resideInAnyPackage("..mapper")
            .should()
            .haveNameMatching(".*Mapper")
            .orShould().haveNameMatching(".*MapperImpl");
}
