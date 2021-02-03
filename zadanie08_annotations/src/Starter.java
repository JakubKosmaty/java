import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.function.Consumer;

public class Starter implements Consumer<String> {
    @Override
    public void accept(String s) {
        try {
            Class<?> tempClass = Class.forName(s);
            Constructor<?> c =  tempClass.getConstructor();
            Object object = c.newInstance();

            Method[] methods = tempClass.getMethods();

            for (Method method: methods) {
                boolean disable = false;
                MethodToStart start = null;
                StringParameter stringParameter = null;

                for (Annotation annotation : method.getDeclaredAnnotations()) {
                    if (annotation instanceof MethodDisabled) {
                        disable = true;
                        continue;
                    }

                    start = method.getAnnotation(MethodToStart.class);
                    stringParameter = method.getAnnotation(StringParameter.class);
                }

                if (disable || start == null) {
                    continue;
                }

                int parameterCount = method.getParameterCount();

                if (parameterCount > 1) {
                    continue;
                }

                if (parameterCount == 1 && !method.getParameterTypes()[0].equals(String.class)) {
                    continue;
                }

                boolean para = stringParameter != null && parameterCount == 1;

                for (int i = 0; i < start.value(); i++) {
                    if (para) {
                        method.invoke(object, stringParameter.value());
                    } else {
                        method.invoke(object);
                    }
                }
            }
        } catch (ClassNotFoundException | InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Consumer<String> andThen(Consumer<? super String> after) {
        return null;
    }
}
