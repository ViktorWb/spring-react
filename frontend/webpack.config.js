const path = require('path')

const mode = process.env.MODE
if (!mode) {
    throw new Error('Expected environment variable "mode"')
}
if (mode !== 'development' && mode !== 'production') {
    throw new Error('Expected environment variable "MODE" to equal "development" or "production"')
}

module.exports = {
    entry: {
        main: './src/index.tsx'
    },
    output: {
        path: path.resolve(__dirname, '..', 'backend', 'src', 'main', 'resources', 'static', 'src'),
        chunkFilename: '[name].bundle.js',
        publicPath: '/src/'
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                use: ['source-map-loader'],
                enforce: 'pre'
            },
            {
                test: /\.tsx?$/,
                loader: 'ts-loader',
                options: { allowTsInNodeModules: true, transpileOnly: true }
            },
            {
                test: /\.css$/i,
                use: ['style-loader', 'css-loader']
            },
            {
                test: /\.woff2?$/i,
                type: 'asset/resource',
                dependency: { not: ['url'] }
            },
            {
                test: /\.(txt|png|svg|jpg|jpeg|gif)$/i,
                type: 'asset/resource'
            }
        ]
    },
    resolve: {
        extensions: ['.tsx', '.ts', '.js']
    },
    devtool: mode === 'development' ? 'source-map' : undefined,
    optimization: {
        minimize: mode === 'production'
    },
    mode
}