package cn.edu.nju.onlineexam.DTO;

/**
 * Created by mac on 2017/12/13.
 */
public class ResultDTO<T> {

    private T object;

    private boolean result;

    private String error;

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
