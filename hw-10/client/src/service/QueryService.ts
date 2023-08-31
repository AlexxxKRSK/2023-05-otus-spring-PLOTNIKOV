
class QueryService {

    public getApi(url: any) {
        return fetch(url).then(resp => resp);
    }
}

export default new QueryService();