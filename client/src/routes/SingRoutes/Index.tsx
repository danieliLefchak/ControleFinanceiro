import { Route, Routes } from "react-router-dom";
import { SignupPage } from "../../pages/SignupPage/Index";
import { LoginPage } from "../../pages/LoginPage/Index";

export function SignRoutes(){
    return(
        <>
            <Routes>
                <Route path="/" element={<LoginPage />}/>
                <Route path="/signup" element={<SignupPage />}/>

                <Route path="*" element={<LoginPage />}/>
            </Routes>
        </>
    )
}