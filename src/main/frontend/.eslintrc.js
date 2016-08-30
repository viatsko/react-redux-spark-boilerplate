module.exports = {
  root: true,

  extends: 'airbnb',

  parser: 'babel-eslint',

  rules: {
    "import/no-extraneous-dependencies": 0,
    "global-require": 0,
    "react/jsx-filename-extension": 0,
    "import/prefer-default-export": 0,
  },

  globals: {
    "document": true,
  },
};
