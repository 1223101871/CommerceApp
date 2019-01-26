package example.com.latte_complier;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.logging.Filter;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

/**
 * created by xcy on 2019/1/24
 **/
public final class EntryVisitor extends SimpleAnnotationValueVisitor7<Void,Void> {
    private Filter mFilter = null;
    private TypeMirror mTypeMirror = null;
    private String mPackageName = null;
    private final Filer FILER;

    EntryVisitor(Filer FILER) {
        this.FILER = FILER;
    }

    public void setFilter(Filter filter){
        this.mFilter = filter;
    }

    @Override
    public Void visitString(String s, Void p) {
        mPackageName = s;
        return p;
    }

    @Override
    public Void visitType(TypeMirror t, Void p) {
        mTypeMirror = t;
        generateJavaCode(t);
        return p;
    }

    private void generateJavaCode(TypeMirror typeMirror) {
        final TypeSpec targetActivity =
                TypeSpec.classBuilder("WXEntryActivity")
                        .addModifiers(Modifier.PUBLIC)
                        .addModifiers(Modifier.FINAL)
                        .superclass(TypeName.get(typeMirror))
                        .build();

        final JavaFile javaFile = JavaFile.builder(mPackageName + ".wxapi", targetActivity)
                .addFileComment("微信入口文件")
                .build();
        try {
            javaFile.writeTo(FILER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
