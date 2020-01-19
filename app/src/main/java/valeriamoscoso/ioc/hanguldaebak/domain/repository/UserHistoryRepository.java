package valeriamoscoso.ioc.hanguldaebak.domain.repository;

import java.util.List;

import valeriamoscoso.ioc.hanguldaebak.data.entity.UserHistory;

/**
 * UserHistoryRepository repository interface with the declaration of the possible server responses
 * @author Valeria Moscoso León
 * */
public interface UserHistoryRepository {

    List<UserHistory> allUserHistory();
}
