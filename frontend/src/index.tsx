import * as React from 'react'
import { createRoot } from 'react-dom/client'

function App() {
    return <div><p>Hello world!</p></div>
}

const root = createRoot(document.getElementById('root') as HTMLElement)
root.render(<App />)
