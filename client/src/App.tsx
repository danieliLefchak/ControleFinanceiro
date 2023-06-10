import { ChakraProvider } from '@chakra-ui/react'
import { BaseRoutes } from './routes/BaseRoutes/Index'

function App() {
  return (
    <ChakraProvider>
      <BaseRoutes/>
    </ChakraProvider>
  )
}

export default App
