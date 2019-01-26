package delegates;

/**
 * created by xcy on 2019/1/20
 **/
public abstract class LatteDelegate extends PermissionCheckerDelegate {
    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
