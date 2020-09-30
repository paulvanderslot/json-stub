package nl.rabobank.powerofattorney;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.library.Architectures;

public class CleanArchitectureTest {
    private static final String DOMAIN_LAYER = "domain";
    private static final String DOMAIN_PACKAGE = "..domain..";
    private static final String APPLICATION_LAYER = "application";
    private static final String APPLICATION_PACKAGE = "..application..";
    private static final String STORAGE_LAYER = "storage";
    private static final String STORAGE_PACKAGE = "..storage..";
    private static final String CONFIG_LAYER = "config";
    private static final String CONFIG_PACKAGE = "..config..";
    private static final String WEB_LAYER = "web";
    private static final String WEB_PACKAGE = "..web..";

    private static final String[] ALL_LAYERS =
            { DOMAIN_LAYER, APPLICATION_LAYER, STORAGE_LAYER, CONFIG_LAYER, WEB_LAYER };

    private final JavaClasses importedClasses = new ClassFileImporter().importPackages("nl.rabobank.powerofattorney");

    @Test
    public void verify_dependency_rules() {
        Architectures.layeredArchitecture()
                .layer(DOMAIN_LAYER).definedBy(DOMAIN_PACKAGE)
                .layer(APPLICATION_LAYER).definedBy(APPLICATION_PACKAGE)
                .layer(STORAGE_LAYER).definedBy(STORAGE_PACKAGE)
                .layer(CONFIG_LAYER).definedBy(CONFIG_PACKAGE)
                .layer(WEB_LAYER).definedBy(WEB_PACKAGE)
                .whereLayer(DOMAIN_LAYER).mayOnlyBeAccessedByLayers(ALL_LAYERS)
                .whereLayer(APPLICATION_LAYER).mayOnlyBeAccessedByLayers(STORAGE_LAYER, WEB_LAYER)
                .whereLayer(STORAGE_LAYER).mayOnlyBeAccessedByLayers()
                .whereLayer(CONFIG_LAYER).mayOnlyBeAccessedByLayers()
                .whereLayer(WEB_LAYER).mayOnlyBeAccessedByLayers()
                .check(importedClasses);
    }
}