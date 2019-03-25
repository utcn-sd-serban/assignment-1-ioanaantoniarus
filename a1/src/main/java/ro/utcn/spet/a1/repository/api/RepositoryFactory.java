package ro.utcn.spet.a1.repository.api;

public interface RepositoryFactory {

    QuestionRepository createQuestionRepository();

    UserRepository createUserRepository();

    TagRepository createTagRepository();

}
