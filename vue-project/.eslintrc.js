module.exports = {
  root: true,
  'extends': [
    'plugin:vue/essential',
    'eslint:recommended'
  ],
    'rules': {
        "vue/no-parsing-error": [2, { "x-invalid-end-tag": false }]
    }
}