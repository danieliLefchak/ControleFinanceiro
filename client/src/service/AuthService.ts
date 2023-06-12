import { IUserLogin, ISignup } from "../commons/interfaces";
import { api } from "../lib/axios"

const signup = (user : ISignup) => {
    return api.post("/users", user);
}

const login = (user : IUserLogin) => {
    return api.post("/login", user);
}

//ver algo na internet sobre esta parte
const isAuthenticated = () =>{
    const token = localStorage.getItem("token");
    if(token){
        api.defaults.headers.common["Authorization"] = `Bearer ${JSON.parse(token)}`
    }

    return token ? true : false;
}

const findUserByName = (user: ISignup) => {
    return api.get(`/users/findUser/${user.username}`);
}

const logout = () => {
    localStorage.removeItem("token");
}

const AuthService = {
    signup,
    login,
    isAuthenticated,
    findUserByName,
    logout,
}

export default AuthService;