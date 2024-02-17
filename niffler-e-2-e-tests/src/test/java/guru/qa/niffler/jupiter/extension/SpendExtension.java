package guru.qa.niffler.jupiter.extension;

import guru.qa.niffler.model.SpendJson;
import org.junit.jupiter.api.extension.ExtensionContext;

public abstract class SpendExtension {

    public static final ExtensionContext.Namespace NAMESPACE
            = ExtensionContext.Namespace.create(SpendExtension.class);

    abstract SpendJson create(SpendJson spend);

}
