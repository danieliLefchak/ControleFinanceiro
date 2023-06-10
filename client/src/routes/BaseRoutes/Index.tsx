import { Route, Routes } from "react-router-dom";
import { LoginPage } from "../../pages/LoginPage/Index";
import { SignupPage } from "../../pages/SignupPage/Index";
import { AuthenticatedRoutes } from "../AuthenticatedRoutes/Index";
import { HomePage } from "../../pages/HomePage/Index";
import { ListContasPage } from "../../pages/ListContasPage/Index";
import { FormContasPage } from "../../pages/FormContasPage/Index";
import { ListMovimentacoes } from "../../pages/ListMovimentacoes/Index";
import { FormMovimentacoes } from "../../pages/FormMovimentacoes/Index";
import { FormTransferencia } from "../../pages/FormTransferencia/Index";

export function BaseRoutes(){
    return(
        <>
            <Routes>
                {/* Public Routes */}
                <Route path="/login" element={<LoginPage />}/>
                <Route path="/signup" element={<SignupPage />}/>

                {/* Protected Routes */}
                <Route element={<AuthenticatedRoutes />}>
                    <Route path="/home" element={<HomePage />}/>
                    <Route path="/" element={<HomePage />}/>

                    <Route path="/contas" element={<ListContasPage />}></Route>
                    <Route path="/contas/new" element={<FormContasPage />}></Route>
                    <Route path="/contas/:id" element={<FormContasPage />}></Route>

                    <Route path="/movimentacoes/findAll/:contaId" element={<ListMovimentacoes />}></Route>
                    <Route path="/movimentacoes/new" element={<FormMovimentacoes />}></Route>
                    <Route path="/movimentacoes/:id" element={<FormMovimentacoes />}></Route>

                    <Route path="/transferencias/new" element={<FormTransferencia />}></Route>
                </Route>
            </Routes>
        </>
    )
}

/* 
    Exemplos

    <Route path="/categories" element={<CategoryListPage />}/>
        <Route path="/categories/new" element={<CategoryFormPage />}/>
        <Route path="/categories/:id" element={<CategoryFormPage />}/>

        <Route path="/products" element={<ProductListPage />}/>
        <Route path="/products/new" element={<ProductFormPage />}/>
        <Route path="/products/:id" element={<ProductFormPage />}/>

        <Route path="/products-v2" element={<ProductListPageV2 />}/>
        <Route path="/products-v2/new" element={<ProductFormPageV2 />}/>
        <Route path="/products-v2/:id" element={<ProductFormPageV2 />}/>
*/