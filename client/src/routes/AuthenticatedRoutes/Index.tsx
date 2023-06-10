import { Navigate, Outlet, useLocation } from "react-router-dom";
import AuthService from "../../service/AuthService";
import { NavBar } from "../../componentes/NavBar/Index";
import { Footer } from "../../componentes/Footer/Index";

export function AuthenticatedRoutes(){
    const isAuthenticatedRoutes = AuthService.isAuthenticated();
    const location = useLocation();
    //outlet serve para renderizar os 
    //componentes que caem dentro do 
    //authenticatedRoutes ele é como 
    //se fosse um "body"
    return isAuthenticatedRoutes ? (
        <>
            <NavBar />
            <Outlet />
            <Footer />
        </>

    ) : (
        <Navigate to="/login" state={{from: location}} replace/>
    )
}