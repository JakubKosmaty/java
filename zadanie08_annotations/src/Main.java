public class Main {
    public static void main(String[] args) {
        Starter starter = new Starter();

        PMO_TestBaseClass pmo_testBaseClass = new PMO_TestBaseClass();
        PMO_Easy pmo_easy = new PMO_Easy();
        PMO_Null pmo_null = new PMO_Null();
        PMO_MultipleMethods pmo_multipleMethods = new PMO_MultipleMethods();
        PMO_EasyParameter pmo_easyParameter = new PMO_EasyParameter();


        //starter.accept(pmo_testBaseClass.getClass().getName());
        starter.accept(pmo_easy.getClass().getName());
        //starter.accept(pmo_null.getClass().getName());
        //starter.accept(pmo_multipleMethods.getClass().getName());
        //starter.accept(pmo_easyParameter.getClass().getName());
    }
}
