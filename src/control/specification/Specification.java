package control.specification;

public interface Specification<T> {
    boolean match(T bean);
}
