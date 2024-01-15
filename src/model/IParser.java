package model;

public interface IParser <E> {
    E parse(String line);
}
